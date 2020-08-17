import { AsyncStorage } from 'react-native';

export default function App() { 

    logar = async(usuario) => {
      const response = await api.post('/sessions', {
        email: usuario.email,
        password: usuario.password,
      });
    }

    
    cadastrar = async(usuario) => {
      const response = await api.post('/sessions', {
        email: usuario.email,
        password: usuario.password,
      });
    }

    _guardarChave = async (token) => {
        try {
          await AsyncStorage.setItem('@SavePet:token', token);


        } catch (error) {
          // Error saving data
        }
      };
      
    _receberChave = async () => {
    try {
        const value = await AsyncStorage.getItem('@SavePet:token');
        if (value !== null) {
        // We have data!!
        console.log(value);
        }
    } catch (error) {
        // Error retrieving data
    }
    };
}
