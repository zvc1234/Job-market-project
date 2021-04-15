import * as React from "react";
import { Menubar } from 'primereact/menubar';
import { LoginService } from "../Services/LoginService";
import { Button } from "primereact/button";
import { Redirect } from 'react-router-dom'
import '../css/Header.css';

export class Header extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            logoutClicked : false
        };
    }

    render() {
        return (
            <div className='p-header-container'>

                <div className='p-menubar-container'>
                    <Menubar model={[
                        {
                            label: 'Dashboard',
                            icon: 'fa fa-calendar-o',
                            url: "/dashboard"
                        },
                        {
                            label: 'Profilul meu',
                            icon: 'fa fa-male',
                            url: "/profile/" + LoginService.getId()
                        },
                        {
                            label: "Adauga Job",
                            icon: "fa fa-suitcase",
                            url: "/add_job"
                        },
                        {
                            label: "Utilizatori",
                            icon: "fa fa-users",
                            url: "/users_list"
                        }

                    ]}>
                        <Button label="Logout" onClick={() => {
                            LoginService.logout();
                            this.setState({logoutClicked : true});
                        }} />
                    </Menubar>
                </div>
                {LoginService.getId() === "" ? <Redirect to="/login" /> : null}
                {this.state.logoutClicked ? this.handleLogout() : null}
            </div>
        )
        
    }

    handleLogout()
    {
        this.setState({logoutClicked : false});
        return(
            <Redirect to="/login" />
        )
    }
}