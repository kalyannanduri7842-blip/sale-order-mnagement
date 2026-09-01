import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import { NotificationProvider } from './context/NotificationContext';
import { ThemeProvider } from './context/ThemeContext';
import { ErpDataProvider } from './context/ErpDataContext';
import { AppRoutes } from './routes/AppRoutes';

export default function App() {
  return (
    <BrowserRouter>
      <ThemeProvider>
        <AuthProvider>
          <ErpDataProvider>
            <NotificationProvider>
              <AppRoutes />
            </NotificationProvider>
          </ErpDataProvider>
        </AuthProvider>
      </ThemeProvider>
    </BrowserRouter>
  );
}
