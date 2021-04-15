import * as React from 'react';
import {JobService} from '../Services/JobService'
import { DataView } from 'primereact/dataview';
import {Panel} from 'primereact/panel';
import { Button } from 'primereact/button';
import { LoginService } from '../Services/LoginService';
import StarRatings from 'react-star-ratings';
import { NotificationManager } from 'react-notifications';

export class JobView extends React.Component
{
    constructor() {
        super();
        this.state = {
            job:{
                id : 0,
                title: "",
                description : "",
                categoryName : "",
                username: "",
                identifyJob : 0
            },
            jobApplyModel:{
                username : "",
                jobId : 0
            },
            users : []
        };
        this.renderJob = this.renderJob.bind(this);
        this.renderUser = this.renderUser.bind(this);
    }

    componentDidMount() {
        let id = this.props.match.params.id;
        JobService.getJob(id).then((response)=>
        {
        this.setState({ job : response });
        let jobApplyModel = this.state.jobApplyModel;
        jobApplyModel.jobId = this.state.job.id;
        jobApplyModel.username =LoginService.getId(); 
        this.setState({ jobApplyModel: jobApplyModel})
        
        if( this.state.job.username === LoginService.getId() ){
            JobService.getAllAplicantsForAJob(id).then((response)=>{
                this.setState({ users : response });
            }, (error) => 
            {
                console.log(error);
            });
        }   

        }, (error) => 
        {
            console.log(error);
        });

          
    }



    renderUser(user)
    {
        return(
            <div style={{ padding: '.5em' }} className="p-col-12 p-md-3">
                <Panel header={user.username} style={{ textAlign: 'center' }}>
                    <p>Telefon : {user.phone}</p>
                    <StarRatings
                        rating={user.stars_average}
                        starRatedColor="blue"
                        starDimension="20px"
                        numberOfStars={5}
                        name='rating'
                        />
                    <p onClick={() => {
                        this.props.history.push("/profile/"+ user.username)}
                        } style={{ color:"blue", cursor: "pointer"}}>Vezi detalii</p>
                    <hr className="ui-widget-content" style={{ borderTop: 0 }} />
                </Panel>
            </div>
        )
    }

    renderJob(){
        return(
            <div id="myprofiletab">
            <p>Categorie  :{this.state.job.categoryName} </p>
            <p>Descriere : {this.state.job.description}</p>
            <p onClick={()=>{this.props.history.push("/profile/"+ this.state.job.username)}}>Provider : {this.state.job.username}</p>
            </div>
            )
    }

    render()
    {
        return (<React.Fragment>
            {
                <div>
                    <div className="content-section introduction">
                        <div className="feature-intro">
                            <h1>Job</h1>
                        </div>
                    </div>
                    <div className="content-section implementation">
                            <Panel header={this.state.job.title} leftIcon="pi pi-user">
                                {this.renderJob()}
                                { this.state.job.username !== LoginService.getId() ? <Button label="Aplica" onClick={()=>{this.applyForJob()}}></Button> : null }
                            </Panel>
                            {this.state.job.username === LoginService.getId() ? <DataView value={this.state.users} layout="grid" itemTemplate={this.renderUser}/> : null }
                            
                    </div>                   
                </div>
            }
        </React.Fragment>);
    }

    applyForJob()
    {
        JobService.applyForJob(this.state.jobApplyModel).then(() => {
            //NotificationManager.success("Ai aplicat cu succes !", "Felicitari !");
            this.props.history.push("/dashboard");
        }, (error) => {
            NotificationManager.error(error.message);
        });
    }
}
