import styled from 'styled-components/native';
import { primary, secondary, warn, alert, backgroundLogin, backgroungInput} from '../colors';

const Logo = styled.Image`
  height: 30%;
  marginBottom: 40px;
`;
const Container = styled.View`
  flex: 1;
  alignItems: center;
  justifyContent: center;
  backgroundColor: ${backgroundLogin};
`;

const Input = styled.TextInput`
  paddingHorizontal: 20px;
  paddingVertical: 15px;
  borderRadius: 5px;
  backgroundColor:  ${backgroungInput};
  alignSelf: stretch;
  marginBottom: 15px;
  marginHorizontal: 20px;
  fontSize: 16px;
`;


const Button = styled.TouchableHighlight`
  padding: 20px;
  borderRadius: 5px;
  backgroundColor: ${primary};
  alignSelf: stretch;
  margin: 15px;
  marginHorizontal: 20px;
`;

const ButtonText = styled.Text`
  color: #fff;
  fontWeight: bold;
  fontSize: 16px;
  textAlign: center;
`;

const SignInLink = styled.TouchableHighlight`
  padding: 10px;
  marginTop: 20px;
`;

const SignInLinkText = styled.Text`
  color: #999;
  fontWeight: bold;
  fontSize: 16px;
  textAlign: center;
`;


const FacebookLink = styled.TouchableHighlight`
  borderRadius: 5px;
  alignSelf: stretch;
  padding: 10px;
  marginTop: 20px;
  backgroundColor: #3b5998;
  margin: 15px;
  marginHorizontal: 20px;
`;

const FacebookLinkText = styled.Text`
  color: #fff;
  fontWeight: bold;
  fontSize: 16px;
  textAlign: center;
`;

export { Logo, Container, Input, Button, ButtonText, SignInLink, SignInLinkText, FacebookLink , FacebookLinkText};
