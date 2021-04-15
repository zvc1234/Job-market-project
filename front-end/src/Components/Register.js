import * as React from "react";
import { InputText } from 'primereact/components/inputtext/InputText';
import { Button } from 'primereact/components/button/Button';
import { Message } from 'primereact/components/message/Message';
import { Calendar } from 'primereact/components/calendar/Calendar';
import { UserService } from "../Services/UserService";
import { NotificationManager } from 'react-notifications';
import '../css/Register.css';
import signup from '../img/avatar3.png';
import { InputTextarea } from "primereact/inputtextarea";

export class Register extends React.Component {

    constructor(usersProps) {
        super(usersProps);

        this.state = {
            userModel: {
                username: "",
                date_of_birth: new Date(),
                email: "",
                first_name: "",
                last_name: "",
                password: "",
                phone: "",
                bio: ""
            },
            errors: {
                username: "",
                date_of_birth: "",
                email: "",
                first_name: "",
                last_name: "",
                password: "",
                phone: "",
                bio: ""
            },
            isValid: false
        };
    }

    submit() {
        let isValid = UserService.validateUser(this.state.userModel, this.state.errors);

        if (isValid) {
            UserService.addUser(this.state.userModel).then(() => {
                //NotificationManager.success("Contul a fost creat !", "Felicitari !");
                this.setState({ isValid: true });
            }, (error) => {
                NotificationManager.error(error.message);
                this.setState({ isValid: false });
            });
        }
        else {
            this.setState({ isValid: false });
        }
    }

    render() {
        return (
            <div id="mainregister">
            <div id="imagesignup">
                <img id="avatar" src = {signup} alt="imagine"></img>
            </div>
            <div id="addUser">
                <h3>Te rugam sa introduci urmatoarele informatii pentru crearea contului tau!</h3>
                <label> Nume utilizator: &nbsp; </label> <br/>
                <InputText id="name" type="text" size={30} onChange={(event) => {
                    let userModel = this.state.userModel;
                    userModel.username = event.target.value;
                    this.setState({ userModel: userModel });
                }} />
                {this.state.errors.username !== "" ? <Message severity="warn" text={this.state.errors.username} /> : null}
                <br /> <br />

                <label> Parola: &nbsp; </label> <br/>
                <InputText id="password" type="password" size={30} onChange={(event) => {
                    let userModel = this.state.userModel;
                    userModel.password = event.target.value;
                    this.setState({ userModel: userModel });
                }} />
                {this.state.errors.password !== "" ? <Message severity="warn" text={this.state.errors.password} /> : null}
                <br /> <br />

                <label> Prenume: &nbsp; </label> <br/>
                <InputText id="first_name" type="text" size={30} onChange={(event) => {
                    let userModel = this.state.userModel;
                    userModel.first_name = event.target.value;
                    this.setState({ userModel: userModel });
                }} />
                {this.state.errors.first_name !== "" ? <Message severity="warn" text={this.state.errors.first_name} /> : null}
                <br /> <br />

                <label> Nume: &nbsp; </label> <br/>
                <InputText id="last_name" type="text" size={30} onChange={(event) => {
                    let userModel = this.state.userModel;
                    userModel.last_name = event.target.value;
                    this.setState({ userModel: userModel });
                }} />
                {this.state.errors.last_name !== "" ? <Message severity="warn" text={this.state.errors.last_name} /> : null}
                <br /> <br />

                <label> Data nasterii: &nbsp;</label> <br/>
                <Calendar dateFormat="dd/mm/yy" value={this.state.userModel.date_of_birth} onChange={(event) => {
                    let userModel = this.state.userModel;
                    userModel.date_of_birth = event.value;
                    this.setState({ userModel: userModel });
                }} />
                {this.state.errors.date_of_birth !== "" ? <Message severity="warn" text={this.state.errors.date_of_birth} /> : null}
                <br /><br />

                <label> E-mail: &nbsp; </label> <br/>
                <InputText id="mail" type="email" size={30} onChange={(event) => {
                    let userModel = this.state.userModel;
                    userModel.email = event.target.value;
                    this.setState({ userModel: userModel });
                }} />
                {this.state.errors.email !== "" ? <Message severity="warn" text={this.state.errors.email} /> : null}
                <br /><br />

                <label> Telefon:&nbsp;  </label> <br/>
                <InputText id="phone" type="text" size={30} onChange={(event) => {
                    let userModel = this.state.userModel;
                    userModel.phone = event.target.value;
                    this.setState({ userModel: userModel });
                }} />
                {this.state.errors.phone !== "" ? <Message severity="warn" text={this.state.errors.phone} /> : null}
                <br /><br />

                <label> Descriere:&nbsp;  </label> <br/>
                <InputTextarea id="bio" type="text" size={200} autoResize="false" onChange={(event) => {
                    let userModel = this.state.userModel;
                    userModel.bio = event.target.value;
                    this.setState({ userModel: userModel });
                }} />
                {this.state.errors.bio !== "" ? <Message severity="warn" text={this.state.errors.bio} /> : null}
                <br /><br />

                <Button label="Register" onClick={() => this.submit()} />

                {this.state.isValid === true ? this.props.history.push({
                    pathname: '/abilities',
                    user: this.state.userModel
               }) : null}

            </div>
            </div>
        );
    }
}