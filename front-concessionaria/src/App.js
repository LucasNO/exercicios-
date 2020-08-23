import React, { Component } from 'react';
import './css/pure-min.css';
import './css/side-menu.css';
import {Link} from 'react-router';

class App extends Component {

  render() {    
    return (
      <div id="layout">
          
          <a href="#menu" id="menuLink" className="menu-link">
              
              <span></span>
          </a>

          <div id="menu">
              <div className="pure-menu">
                  <a className="pure-menu-heading" href="#">Company</a>

                  <ul className="pure-menu-list">
                      <li className="pure-menu-item"><Link to="/" className="pure-menu-link">Home</Link></li>
                      <li className="pure-menu-item"><Link to="/ultimos-cadastrados" className="pure-menu-link">Ultimos Cadastrados</Link></li>
                      <li className="pure-menu-item"><Link to="/carros-por-marca" className="pure-menu-link">Carros por Marca</Link></li>
                      <li className="pure-menu-item"><Link to="/carros-por-decada" className="pure-menu-link">Carros por Decada</Link></li>
                      <li className="pure-menu-item"><Link to="/carros-nao-vendido" className="pure-menu-link">Carros não Vendido</Link></li>

                      
                  </ul>
              </div>
          </div>

              <div id="main">
                {this.props.children}
              </div>            


      </div>     
    );
  }
}

export default App;
