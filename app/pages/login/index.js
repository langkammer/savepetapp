import React, { Component } from 'react';

import pata from'../../assets/pata.png';

import {
  Container,
  Input,
  Button,
  ButtonText,
  SignInLink,
  Logo,
  SignInLinkText,
  FacebookLink,
  FacebookLinkText
} from './styles';

export default class Login extends Component {


  logar = () => {
      console.log("teste");
  };
  
  cadastrar = () => {
    console.log("teste 2");
    this.props.navigation.navigate('Cadastrar');

  }

  render() {
    return (
      <>
     <Container>
        <Logo source={pata} resizeMode="contain" />
        <Input
          placeholder="Endereço de e-mail"
          autoCapitalize="none"
          autoCorrect={false}
        />
        <Input
          placeholder="Senha"
          autoCapitalize="none"
          autoCorrect={false}
          secureTextEntry
        />
     
      <Button >
            <ButtonText onClick={this.logar}>Entrar </ButtonText>
      </Button>
	    <FacebookLink>
		      <FacebookLinkText>Logar Com Facebook</FacebookLinkText>
		  </FacebookLink>
      <SignInLink onClick={this.cadastrar}>
        <SignInLinkText>Criar conta</SignInLinkText>
      </SignInLink>
      </Container>
      
      </>
 
  );
  }
  
}

