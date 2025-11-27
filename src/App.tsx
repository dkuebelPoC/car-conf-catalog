import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ConfiguratorPage from './pages/ConfiguratorPage';
import OrderPage from './pages/OrderPage';

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<ConfiguratorPage />} />
        <Route path="/config/:id" element={<ConfiguratorPage />} />
        <Route path="/order/:id" element={<OrderPage />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
