import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Marcas from './Marcas';
import VeiculosDecada from './VeiculosDecada';
import UltimosCadastrados from './UltimosCadastrados';
import CarrosNaoVendidos from './CarrosNaoVendidos';
import Home from './Home';
import './index.css';
import {Router,Route,browserHistory,IndexRoute} from 'react-router';

ReactDOM.render(
  (<Router history={browserHistory}>
  	<Route path="/" component={App}>
  		<IndexRoute component={Home}/>
	  	<Route path="/ultimos-cadastrados" component={UltimosCadastrados}/>
	  	<Route path="/carros-por-marca" component={Marcas}/>
	  	<Route path="/carros-por-decada" component={VeiculosDecada}/>
	  	<Route path="/carros-nao-vendido" component={CarrosNaoVendidos}/>
  	</Route>
  </Router>),
  document.getElementById('root')
);
