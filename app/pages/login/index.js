import React from 'react';

import pata from'../../assets/pata.png';

import {
  Container,
  Input,
  Button,
  ButtonText,
  SignInLink,
  Logo,
  SignInLinkText,
} from './styles';

const Login = () => {

   function logar() {
      console.log("teste");
  }

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
                <ButtonText onClick={logar}>Entrar </ButtonText>
          </Button>
          
        <SignInLink >
          <SignInLinkText>Criar conta grátis</SignInLinkText>
        </SignInLink>
      </Container>
      
      </>
 
  )
}

export default Login;