import { DataView } from 'primereact/dataview';
import { Panel } from 'primereact/panel';
import {InputText} from 'primereact/inputtext';
import React from 'react';
import { UserService } from '../Services/UserService';
import StarRatings from 'react-star-ratings';
import '../css/ListUser.css';

export class UsersList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
            filteredUsers:[]
        };
        this.renderUser = this.renderUser.bind(this);
    }

    loadAllUsers(){
        UserService.getAllUsers().then((response) =>{
            this.setState({users : response});
            this.setState({filteredUsers : response});
        }, (error) => {
            console.log(error.message);
        })
    }

    searchForUsers(value){
        let filteredUsers = this.state.filteredUsers;

            filteredUsers = this.state.users.filter((user) => {    
                return user.username.includes(value);
            })
            this.setState({filteredUsers : filteredUsers})
   }

    componentDidMount() {
        this.loadAllUsers();
    }

    renderUser(user) {
        return (
            <div style={{ padding: '.5em' }} className="p-col-12 p-md-3">
                <Panel className='userElem' header={user.username} style={{ textAlign: 'center' }}>
                    <p>Telefon : {user.phone}</p>
                    <StarRatings
                        rating={user.stars_average}
                        starRatedColor="orange"
                        numberOfStars={5}
                        name='rating'
                        />
                    <p onClick={() => {this.redirectToUser(user.username)}} style={{ color:"orange", cursor: "pointer"}}>Vezi detalii</p>
                    <hr className="ui-widget-content" style={{ borderTop: 0 }} />
                </Panel>
            </div>
        )
    }

    redirectToUser(user){
        this.props.history.push("/profile/"+ user);
    }

    render() {
        return (
            <React.Fragment>
                    <div className="p-search-users">
                        <span className="p-float-label">
                            Cauta user:  
                            <InputText 
                                value={this.state.userSearch} 
                                onChange={(e) => {
                                    this.searchForUsers(e.target.value)
                                }} />
                        </span>
                    </div>
                <div className="p-grid p-fluid">
                    <DataView className='p-user-list' value={this.state.filteredUsers} layout='grid' itemTemplate={this.renderUser} />   
                </div>
            </React.Fragment>

        )
    }
}
