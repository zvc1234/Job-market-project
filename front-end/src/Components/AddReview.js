import * as React from "react";
import { Button } from 'primereact/components/button/Button';
import { NotificationManager } from 'react-notifications';
import { InputTextarea } from 'primereact/inputtextarea';
import StarRatings from 'react-star-ratings';
import { ReviewService } from '../Services/ReviewService'
import { LoginService } from "../Services/LoginService";
import { JobService } from '../Services/JobService';
import {NotificationService} from '../Services/NotificationService';
import {UserService} from '../Services/UserService'
import {InputText} from 'primereact/inputtext'
import '../css/Review.css';


export class AddReview extends React.Component {

    constructor(userProps) {
        super(userProps);

        this.state = {
            reviewModel: {
                comment: "",
                stars: 1,
                receiver_username: "",
                writer_username: LoginService.getId()
            },
            jobId: userProps.match.params.id,
            userList : []
        }

    }

    componentDidMount() {

        UserService.getAllUsernames().then((response) =>{
            this.setState({userList : response});
        }, (error) => {
            console.log(error.message);
        })
    }


    submit() {
        ReviewService.addReview(this.state.reviewModel).then(() => {
            //NotificationManager.success("Review adaugat cu succes !", "Felicitari !");
            let notification = {
                from_user : this.state.reviewModel.writer_username,
                to_user : this.state.reviewModel.receiver_username,
                message : "[add_review/"+this.state.jobId+"] REVIEW : " + this.state.reviewModel.writer_username + "v-a facut un review. Daca doriti sa faceti si lui dati click aici."
            }
            NotificationService.addNotification(notification);
            this.deleteJob(this.state.jobId);
            this.props.history.goBack();
        }, (error) => {
            NotificationManager.error("Review-ul nu s-a adaugat: " + error.message);
        });
    }

    deleteJob(jobId) {
        JobService.deleteJob(jobId).then(() => {
            //NotificationManager.success('Job sters cu succes');
        }, (error) => {
            //NotificationManager.error('Error at deleting job: ' + error);
        })
    }

    render() {
        return (
            <div className="container">
                <div className="p-grid p-fluid">
                    <h3>Review</h3>
                    <label> Username : &nbsp; </label>
                    <InputText id="username" type="text" size={70} onChange={(event) => {
                        let reviewModel = this.state.reviewModel;
                        reviewModel.receiver_username = event.target.value;
                        this.setState({ reviewModel: reviewModel });
                    }} />
                    <br /> <br />

                    <label> Comment : &nbsp; </label>
                    <InputTextarea rows={5} cols={60} autoResize={true} onChange={(event) => {
                        let reviewModel = this.state.reviewModel;
                        reviewModel.comment = event.target.value;
                        this.setState({ reviewModel: reviewModel });
                    }} />
                    <br /> <br />

                    <StarRatings
                        rating={this.state.reviewModel.stars}
                        starRatedColor="orange"
                        changeRating={(event) => {
                            let reviewModel = this.state.reviewModel;
                            reviewModel.stars = event;
                            this.setState({ reviewModel: reviewModel });
                        }}
                        numberOfStars={5}
                        name='rating'
                    />

                    <br /> <br />
                    <Button id="b" label="Adauga review" onClick={() => this.submit()} />

                </div>
            </div>
        );
    }

}