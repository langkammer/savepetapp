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
  FacebookLink,
  FacebookLinkText
} from './styles';

const Cadastrar = () => {

   function salvar() {
      console.log("teste");
  }

  function cadastrar() {
    console.log("teste ... >>");

  };

  return (
      <>
     <Container>
        <Logo source={pata} resizeMode="contain" />
        <Input
          placeholder="Nome"
          autoCapitalize="none"
          autoCorrect={false}
        />
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
            <ButtonText onPress={salvar}>Salvar </ButtonText>
      </Button>
	  
      </Container>
      
      </>
 
  )
}

export default Cadastrar;