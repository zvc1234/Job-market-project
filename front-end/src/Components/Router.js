import * as React from "react";
import { Route, withRouter } from 'react-router-dom';
import { Login } from "./Login";
import { Register } from "./Register";
import { Dashboard } from "./Dashboard";
import { UserProfile } from "./UserProfile";
import { JobForm } from "./JobForm";
import { AddReview } from "./AddReview";
import {Abilities} from "./Abilities"
import { UsersList } from "./UsersList";
import { JobView } from "./JobView";

export class Router extends React.Component {

    render() {
        return (
            <div>
                <Route path="/" exact={true} component={Login}> </Route>
                <Route path="/login" exact={true} component={Login}> </Route>
                <Route path='/dashboard' exact={true} component = {Dashboard}></Route>
                <Route path="/register" exact={true} component={Register}> </Route>
                <Route path="/profile/:username" exact={true} component={withRouter(UserProfile)}> </Route>
                <Route path="/add_job" exact={true} component={JobForm}> </Route>
                <Route path="/add_review/:id" exact={true} component={AddReview}> </Route>
                <Route path="/abilities" exact={true} component={Abilities}></Route>
                <Route path="/users_list" exact={true} component={UsersList}></Route>
                <Route path="/job/:id" exact={true} component={JobView}></Route>
            </div>
        );
    }
}