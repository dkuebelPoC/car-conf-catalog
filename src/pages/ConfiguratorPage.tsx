import React from 'react';
import OptionSelector from '../components/OptionSelector';
import Summary from '../components/Summary';

const ConfiguratorPage: React.FC = () => {
  return (
    <div style={{ padding: '1rem' }}>
      <h1>KFZ-Konfigurator</h1>
      <p>Diese Seite wird Optionen laden und eine Konfiguration ermöglichen.</p>
      <OptionSelector />
      <Summary />
    </div>
  );
};

export default ConfiguratorPage;
