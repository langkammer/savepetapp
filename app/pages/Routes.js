import React from 'react';

import { createStackNavigator } from '@react-navigation/stack';

import  Login  from './login';
import  Home  from './home';
import  Perfil  from './perfil';
import  Cadastrar from './cadastrar'

const Stack = createStackNavigator();

const Routes = () => {
  return (
    <Stack.Navigator>
        <Stack.Screen name="Login" component={Login} options={{ headerShown: false }} />
        <Stack.Screen name="Cadastrar" component={Cadastrar} />
        <Stack.Screen name="Home" component={Home} />
        <Stack.Screen name="Perfil" component={Perfil} />
    </Stack.Navigator>
  );
};

export default Routes;