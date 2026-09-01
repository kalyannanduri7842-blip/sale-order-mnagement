import React, { createContext, useContext, useState, useEffect } from 'react';
import { api } from '../services/api';
import { ROLES, hasPermission } from '../constants/roles';

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('erp_user');
    if (saved) {
      try {
        return JSON.parse(saved);
      } catch (e) {
        return null;
      }
    }
    // Default logged in user for immediate experience
    return {
      id: 1,
      username: 'admin',
      fullName: 'Super Administrator',
      email: 'admin@enterprisepro.com',
      roles: [ROLES.SUPER_ADMIN, ROLES.ADMIN, ROLES.HR_MANAGER, ROLES.FINANCE_MANAGER, ROLES.INVENTORY_MANAGER, ROLES.SALES_MANAGER, ROLES.PROJECT_MANAGER]
    };
  });

  const [token, setToken] = useState(() => localStorage.getItem('erp_token') || 'mock_jwt_token_admin');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (user) {
      localStorage.setItem('erp_user', JSON.stringify(user));
    } else {
      localStorage.removeItem('erp_user');
    }
  }, [user]);

  useEffect(() => {
    if (token) {
      localStorage.setItem('erp_token', token);
    } else {
      localStorage.removeItem('erp_token');
    }
  }, [token]);

  const login = async (usernameOrEmail, password) => {
    setLoading(true);
    try {
      const data = await api.auth.login({ usernameOrEmail, password });
      setUser({
        id: data.id,
        username: data.username,
        email: data.email,
        fullName: data.fullName,
        roles: data.roles || [ROLES.EMPLOYEE]
      });
      setToken(data.accessToken);
      return { success: true };
    } catch (err) {
      return { success: false, error: err.response?.data?.message || 'Login failed' };
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    setUser(null);
    setToken(null);
    localStorage.removeItem('erp_token');
    localStorage.removeItem('erp_user');
  };

  const checkRole = (allowedRoles) => {
    if (!user || !user.roles) return false;
    return hasPermission(user.roles, allowedRoles);
  };

  const switchRoleDemo = (roleKey) => {
    const role = ROLES[roleKey] || ROLES.EMPLOYEE;
    const nameMap = {
      SUPER_ADMIN: 'Super Administrator',
      HR_MANAGER: 'Sarah Jenkins (HR)',
      FINANCE_MANAGER: 'Robert Sterling (CFO)',
      INVENTORY_MANAGER: 'Marcus Vance (Logistics)',
      SALES_MANAGER: 'Elena Rostova (Sales)',
      PROJECT_MANAGER: 'Alex Rivers (PM)'
    };
    setUser({
      ...user,
      fullName: nameMap[roleKey] || 'Enterprise Staff',
      roles: [role, ROLES.EMPLOYEE]
    });
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        token,
        loading,
        login,
        logout,
        checkRole,
        switchRoleDemo,
        isAuthenticated: !!user
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
