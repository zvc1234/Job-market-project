import * as React from 'react';
import './App.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primereact/resources/themes/nova-light/theme.css';
import 'font-awesome/css/font-awesome.css';
import 'react-notifications/lib/notifications.css';
import { BrowserRouter } from 'react-router-dom';
import { Header } from './Components/Header';
import { NotificationContainer } from 'react-notifications';
import { Router } from './Components/Router';

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <div>
          <NotificationContainer />
          <BrowserRouter>
            <div className='App site'>
              <div className='site-content'>
                  <Header />
                <div className='main'>
                  <Router />
                </div>
              </div>
            </div>
          </BrowserRouter>
        </div>
      </div>
    );
  }
}

export default App;