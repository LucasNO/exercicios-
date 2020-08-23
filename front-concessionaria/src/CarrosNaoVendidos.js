import React, { Component } from 'react';
import $ from 'jquery';

class TabelaCarrosNaoVendidos extends Component {

	render() {
        var quantidade = this.props.quantidade;
		return(
                    <div>
                        <h2>{quantidade}</h2>
                    </div>
		);
	}
}

export default class CarrosNaoVendidosBox extends Component {

    constructor() {
        super();
        this.state = {quantidade : ''};
    }


  componentDidMount(){  
    $.ajax({
        url:"http://localhost:8080/veiculos/quantidade-nao-vendidos",
        dataType: 'json',
        success:function(resposta){
            console.log(resposta);
            this.setState({quantidade: resposta});
        }.bind(this)
      } 
    );
  }
  render() {
    return(
        <div>
          <div className="header">
            <h1>Quantidade de carros Não Vendidos</h1>
          </div>
          <div className="content" id="content">
            <TabelaCarrosNaoVendidos quantidade={this.state.quantidade}/>
          </div>
        </div>
    );
  }
}