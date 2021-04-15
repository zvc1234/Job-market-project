import * as React from 'react';
import { InputText } from 'primereact/components/inputtext/InputText';
import { Button } from 'primereact/components/button/Button';
import { LoginService } from '../Services/LoginService';
import { NotificationManager } from 'react-notifications';
import '../css/Login.css'
import signup from '../img/avatar3.png';


export class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            loginModel: {
                username: '',
                password: ''
            }
        }
    }

    handleKeyPress = (event) => {
        if(event.key === 'Enter'){
         this.submit();
        }
      }

    submit() {
        LoginService.login(this.state.loginModel, this.state.type).then((response) => {
            LoginService.setData(this.state.loginModel.username);
            //NotificationManager.success('You are now logged in');
            this.props.history.push("/dashboard");
        }, (error) => {
            NotificationManager.error('Invalid username or password');
        })
    }

    render() {
        return (<React.Fragment>
            {
                
                <React.Fragment >
                    <div className="container">
                        <div className="p-grid p-fluid">
                            <div className="head">
                                <img id="avatar" src = {signup} alt="signup"></img>
                            </div>
                            <div className="p-col-12 p-md-4">
                                        <InputText  placeholder="Nume utilizator" type="text" size={30} onChange={(event) => {
                                            let loginModel = this.state.loginModel;
                                            loginModel.username = event.target.value;
                                            this.setState({ loginModel: loginModel })
                                        }} />
                            </div>
                            <br></br>
                            <div className="p-col-12 p-md-4">
                                        <InputText placeholder="Parola" type="password" onKeyPress={this.handleKeyPress} size={30} onChange={(event) => {
                                            let loginModel = this.state.loginModel;
                                            loginModel.password = event.target.value;
                                            this.setState({ loginModel: loginModel })
                                        }} />
                                         
                            </div>
                            <div >
                            <Button className='btnn' label="Login" style={{ 'marginTop': `20px`, 'width': '48%', 'marginRight' : '5%'}} onClick={(e) => {
                                this.submit(e);
                            }} />
                            <Button className='btnn' label="Inregistrare" style={{ 'marginTop': `5px`, 'width': '47%'}} onClick={(e) => {
                                this.props.history.push("/register");;
                            }} />
                            </div>
                        </div>
                    </div>

                </React.Fragment>
            }
        </React.Fragment>);
    }

    
}