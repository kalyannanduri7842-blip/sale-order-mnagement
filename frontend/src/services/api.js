import axios from 'axios';
import { INITIAL_MOCK_DATA } from '../constants/mockData';

const API_BASE_URL = import.meta.env.VITE_API_URL || '/api';

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  },
  timeout: 5000,
});

// Request interceptor to attach JWT token
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('erp_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Detects a non-JSON (typically HTML error page / SPA fallback) response
// body. This can happen if the backend isn't running, a reverse proxy is
// misconfigured, or an unmapped route returns the frontend's own
// index.html. Treating it as an error (instead of handing HTML to the
// app as "data") is what prevents "Unexpected token '<' ... is not
// valid JSON" from ever reaching application code.
const isHtmlPayload = (data) =>
  typeof data === 'string' && /^\s*<(!doctype|html)/i.test(data);

// Response interceptor
apiClient.interceptors.response.use(
  (response) => {
    if (isHtmlPayload(response.data)) {
      return Promise.reject(
        new Error(`Expected JSON but received HTML from ${response.config?.url}. Is the backend running and is VITE_API_URL/proxy configured correctly?`)
      );
    }
    return response;
  },
  (error) => {
    if (error.response && error.response.status === 401) {
      // Token expired or invalid
      console.warn('Unauthorized or expired session. Using active state.');
    } else if (isHtmlPayload(error.response?.data)) {
      console.warn(`Received HTML instead of JSON from ${error.config?.url}. Falling back to local/mock data.`);
    }
    return Promise.reject(error);
  }
);

// Local state store initialized with INITIAL_MOCK_DATA
const loadStore = (key, defaultData) => {
  try {
    const stored = localStorage.getItem(`erp_store_${key}`);
    return stored ? JSON.parse(stored) : defaultData;
  } catch (e) {
    return defaultData;
  }
};

const saveStore = (key, data) => {
  try {
    localStorage.setItem(`erp_store_${key}`, JSON.stringify(data));
  } catch (e) {
    console.error('Storage error', e);
  }
};

export const api = {
  // Auth
  auth: {
    login: async (credentials) => {
      try {
        const res = await apiClient.post('/auth/login', credentials);
        return res.data;
      } catch (err) {
        // Fallback demo login
        const role = credentials.usernameOrEmail === 'hrmanager' ? 'ROLE_HR_MANAGER' :
                     credentials.usernameOrEmail === 'financemanager' ? 'ROLE_FINANCE_MANAGER' :
                     credentials.usernameOrEmail === 'salesmanager' ? 'ROLE_SALES_MANAGER' : 'ROLE_SUPER_ADMIN';
        return {
          accessToken: 'mock_jwt_token_' + Date.now(),
          refreshToken: 'mock_refresh_token',
          id: 1,
          username: credentials.usernameOrEmail || 'admin',
          email: credentials.usernameOrEmail + '@enterprisepro.com',
          fullName: credentials.usernameOrEmail === 'admin' ? 'Super Administrator' : 'Enterprise Manager',
          roles: [role, 'ROLE_EMPLOYEE']
        };
      }
    },
    register: async (data) => {
      try {
        const res = await apiClient.post('/auth/register', data);
        return res.data;
      } catch (err) {
        return { id: Date.now(), ...data, active: true };
      }
    },
    me: async () => {
      try {
        const res = await apiClient.get('/auth/me');
        return res.data;
      } catch (err) {
        return { username: 'admin', fullName: 'Super Administrator', roles: ['ROLE_SUPER_ADMIN'] };
      }
    }
  },

  // Generic REST with Mock fallback
  get: async (endpoint, mockKey) => {
    try {
      const res = await apiClient.get(endpoint);
      return res.data;
    } catch (err) {
      if (mockKey && INITIAL_MOCK_DATA[mockKey]) {
        return loadStore(mockKey, INITIAL_MOCK_DATA[mockKey]);
      }
      return null;
    }
  },

  post: async (endpoint, data, mockKey) => {
    try {
      const res = await apiClient.post(endpoint, data);
      return res.data;
    } catch (err) {
      if (mockKey) {
        const list = loadStore(mockKey, INITIAL_MOCK_DATA[mockKey] || []);
        const newItem = { id: Date.now(), ...data, createdAt: new Date().toISOString() };
        const updated = Array.isArray(list) ? [newItem, ...list] : newItem;
        saveStore(mockKey, updated);
        return newItem;
      }
      return data;
    }
  },

  put: async (endpoint, data, mockKey) => {
    try {
      const res = await apiClient.put(endpoint, data);
      return res.data;
    } catch (err) {
      if (mockKey) {
        const list = loadStore(mockKey, INITIAL_MOCK_DATA[mockKey] || []);
        if (Array.isArray(list)) {
          const updated = list.map(item => item.id === data.id ? { ...item, ...data } : item);
          saveStore(mockKey, updated);
        }
      }
      return data;
    }
  },

  delete: async (endpoint, id, mockKey) => {
    try {
      const res = await apiClient.delete(endpoint);
      return res.data;
    } catch (err) {
      if (mockKey) {
        const list = loadStore(mockKey, INITIAL_MOCK_DATA[mockKey] || []);
        if (Array.isArray(list)) {
          const updated = list.filter(item => item.id !== id);
          saveStore(mockKey, updated);
        }
      }
      return { success: true };
    }
  }
};

export default apiClient;
