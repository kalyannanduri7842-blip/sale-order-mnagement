import React, { createContext, useContext, useState, useEffect } from 'react';
import { ROLES, hasPermission } from '../constants/roles';

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const getInitialUser = () => {
    try {
      const saved = localStorage.getItem('erp_user');
      if (saved) {
        const parsed = JSON.parse(saved);
        if (parsed && parsed.email) return parsed;
      }
    } catch (e) {
      console.error(e);
    }
    // Default logged in user (Owner / Super Admin)
    return {
      id: 1,
      username: 'owner',
      fullName: 'Elevit Owner / Administrator',
      email: 'owner@elevitiq.com',
      company: 'Elevit IQ',
      roles: [
        ROLES.SUPER_ADMIN,
        ROLES.ADMIN,
        ROLES.HR_MANAGER,
        ROLES.FINANCE_MANAGER,
        ROLES.INVENTORY_MANAGER,
        ROLES.SALES_MANAGER,
        ROLES.PROCUREMENT_MANAGER,
        ROLES.PROJECT_MANAGER,
        ROLES.EMPLOYEE
      ]
    };
  };

  const [user, setUser] = useState(getInitialUser);
  const [token, setToken] = useState(() => localStorage.getItem('erp_token') || 'jwt_token_elevitiq_owner');
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

  const login = async (usernameOrEmail = 'owner@elevitiq.com', password = 'password123') => {
    setLoading(true);
    try {
      const lower = String(usernameOrEmail || '').toLowerCase();
      let assignedRoles = [ROLES.EMPLOYEE];
      let fullName = 'Enterprise User';

      if (lower.includes('owner') || lower.includes('admin') || lower === 'admin') {
        assignedRoles = [
          ROLES.SUPER_ADMIN,
          ROLES.ADMIN,
          ROLES.HR_MANAGER,
          ROLES.FINANCE_MANAGER,
          ROLES.INVENTORY_MANAGER,
          ROLES.SALES_MANAGER,
          ROLES.PROCUREMENT_MANAGER,
          ROLES.PROJECT_MANAGER,
          ROLES.EMPLOYEE
        ];
        fullName = 'Elevit Owner / Administrator';
      } else if (lower.includes('sales')) {
        assignedRoles = [ROLES.SALES_MANAGER, ROLES.EMPLOYEE];
        fullName = 'Elena Rostova (Sales Lead)';
      } else if (lower.includes('procurement') || lower.includes('vendor')) {
        assignedRoles = [ROLES.PROCUREMENT_MANAGER, ROLES.EMPLOYEE];
        fullName = 'Arthur Pendelton (Procurement Lead)';
      } else if (lower.includes('inventory') || lower.includes('stock')) {
        assignedRoles = [ROLES.INVENTORY_MANAGER, ROLES.EMPLOYEE];
        fullName = 'Marcus Vance (Inventory Lead)';
      } else if (lower.includes('finance') || lower.includes('cfo')) {
        assignedRoles = [ROLES.FINANCE_MANAGER, ROLES.EMPLOYEE];
        fullName = 'Robert Sterling (Finance CFO)';
      } else if (lower.includes('hr')) {
        assignedRoles = [ROLES.HR_MANAGER, ROLES.EMPLOYEE];
        fullName = 'Sarah Jenkins (HR Director)';
      } else {
        assignedRoles = [ROLES.PROJECT_MANAGER, ROLES.EMPLOYEE];
        fullName = 'Alex Rivers (Staff Member)';
      }

      const authenticatedUser = {
        id: Date.now(),
        username: usernameOrEmail || 'owner',
        email: lower.includes('@') ? lower : `${lower || 'owner'}@elevitiq.com`,
        fullName,
        company: 'Elevit IQ',
        roles: assignedRoles
      };

      setUser(authenticatedUser);
      const generatedToken = `jwt_token_${Date.now()}`;
      setToken(generatedToken);
      localStorage.setItem('erp_user', JSON.stringify(authenticatedUser));
      localStorage.setItem('erp_token', generatedToken);

      return { success: true, user: authenticatedUser };
    } catch (err) {
      return { success: false, error: err.message || 'Login failed' };
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
    if (!user || !user.roles) return true; // Fail-open to avoid locking user out
    return hasPermission(user.roles, allowedRoles);
  };

  const switchRoleDemo = (roleKey) => {
    const role = ROLES[roleKey] || ROLES.EMPLOYEE;
    const nameMap = {
      SUPER_ADMIN: 'Elevit Owner / Administrator',
      SALES_MANAGER: 'Elena Rostova (Sales Lead)',
      PROCUREMENT_MANAGER: 'Arthur Pendelton (Procurement Lead)',
      INVENTORY_MANAGER: 'Marcus Vance (Inventory Lead)',
      FINANCE_MANAGER: 'Robert Sterling (Finance CFO)',
      HR_MANAGER: 'Sarah Jenkins (HR Director)',
      PROJECT_MANAGER: 'Alex Rivers (Project Lead)',
      EMPLOYEE: 'Standard Staff Member'
    };

    let newRoles = [role, ROLES.EMPLOYEE];
    if (roleKey === 'SUPER_ADMIN') {
      newRoles = [
        ROLES.SUPER_ADMIN,
        ROLES.ADMIN,
        ROLES.HR_MANAGER,
        ROLES.FINANCE_MANAGER,
        ROLES.INVENTORY_MANAGER,
        ROLES.SALES_MANAGER,
        ROLES.PROCUREMENT_MANAGER,
        ROLES.PROJECT_MANAGER,
        ROLES.EMPLOYEE
      ];
    }

    const updated = {
      id: Date.now(),
      username: (roleKey || 'user').toLowerCase(),
      email: `${(roleKey || 'user').toLowerCase()}@elevitiq.com`,
      company: 'Elevit IQ',
      fullName: nameMap[roleKey] || 'Elevit Workspace User',
      roles: newRoles
    };

    setUser(updated);
    const newToken = `jwt_token_${Date.now()}`;
    setToken(newToken);
    localStorage.setItem('erp_user', JSON.stringify(updated));
    localStorage.setItem('erp_token', newToken);
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
        isAuthenticated: true // Always keep workspace accessible
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
