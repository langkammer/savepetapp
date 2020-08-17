import { AsyncStorage } from 'react-native';


export default function App() { 


    _guardarChave = async () => {
        try {
          await AsyncStorage.setItem(
            '@MySuperStore:key',
            'I like to save it.'
          );
        } catch (error) {
          // Error saving data
        }
      };
      
    _receberChave = async () => {
    try {
        const value = await AsyncStorage.getItem('TASKS');
        if (value !== null) {
        // We have data!!
        console.log(value);
        }
    } catch (error) {
        // Error retrieving data
    }
    };
}
