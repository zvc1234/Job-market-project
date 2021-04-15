import * as React from 'react';
import { DataView } from 'primereact/dataview';
import { Panel } from 'primereact/panel';
import { JobService } from '../Services/JobService';
import { Button } from 'primereact/components/button/Button';
import { UserService } from '../Services/UserService';
import { ReviewService } from '../Services/ReviewService';
import { LoginService } from '../Services/LoginService';
import { TabView, TabPanel } from 'primereact/tabview';
import StarRatings from 'react-star-ratings';
import { Dropdown } from 'primereact/dropdown';
import { NotificationService } from '../Services/NotificationService';
import { NotificationManager } from 'react-notifications';
import Autocomplete from 'react-autocomplete'
import '../css/UserProfile.css';

export class UserProfile extends React.Component {

    constructor() {
        super();
        this.state = {
            user: {
                username: "",
                bio: "",
                date_of_birth: "",
                email: "",
                first_name: "",
                last_name: "",
                password: "",
                phone: "",
                stars_average: 0
            },
            jobs: [],
            reviews: [],
            notifications: [],
            deleteJobId: 0,
            possibleJobs: [],
            users: [],
            chosenPossibleJob: "job",
            notification: {
                from_user: "",
                to_user: "",
                message: ""
            },
            selectedUser: "",
            posibelJobSelected: false,
            isLoading: true
        };
        this.renderJob = this.renderJob.bind(this);
        this.renderNotification = this.renderNotification.bind(this);
    }

    componentDidMount() {
        let username = this.props.match.params.username;
        UserService.getUser(username).then((response) => {
            this.setState({ user: response });
        }, (error) => {
            console.log(error.message);
        })
        ReviewService.getReviewsByUsernameReceiver(username).then((response) => {
            this.setState({ reviews: response });
        }, (error) => {
            console.log(error.message);
        })
        JobService.getJobsForReceiver(username).then((response) => {
            this.setState({ jobs: response });
        }, (error) => {
            console.log(error.message);
        })
        JobService.getJobsForReceiver(LoginService.getId()).then((response) => {
            let possibleJobs = []
            response.map((job) => possibleJobs.push({ name: job["title"], id: job["id"] }));
            this.setState({ possibleJobs: possibleJobs });
        }, (error) => {
            console.log(error.message);
        })
        NotificationService.getNotificationsTo(username).then((response) => {
            this.setState({ notifications: response });
        }, (error) => {
            console.log(error.message);
        })
        UserService.getAllUsers().then((response) => {
            let usernames = response.map((user) => user.username);
            this.setState({ users: usernames });
        }, (error) => {
            console.log(error.message);
        })
    }

    componentDidUpdate() {
        let username = this.props.match.params.username;

        if (this.state.isLoading === true) {
            JobService.getJobsForReceiver(username).then((response) => {
                this.setState({ jobs: response });
                this.setState({ isLoading: false })
            }, (error) => {
                    console.log(error.message);
                })
            UserService.getUser(username).then((response) => {
                this.setState({ user: response });
                this.setState({ isLoading: false });
            }, (error) => {
                    console.log(error.message);
                });
        }
    }

    renderJob(job) {
        return (
            <div style={{ padding: '.5em' }} className="p-col-12 p-md-3">
                <Panel header={job.title} style={{ textAlign: 'center' }}>
                    <b>Categorie : {job.category}</b><br></br>
                    <div className="job-description">
                        {job.description}
                    </div>
                    <p style={{ color: "orange", cursor: "pointer" }} onClick={() => { this.props.history.push("/job/" + job.id); }}>Vezi detalii</p>
                    <hr className="ui-widget-content" style={{ borderTop: 0 }} />
                    { this.state.user.username === LoginService.getId() ? 
                    <Button id="b" label="Sterge job" onClick={() => {
                        this.props.history.push("/add_review/"+job.id);
                    }}/> : null }
                </Panel>
            </div>
        );
    }

    renderReview(review) {
        return (
            <div style={{ padding: '.5em' }} className="p-col-12 p-md-3">
                <Panel header={review.id_review} style={{ textAlign: 'center' }}>
                    <b>Nume: {review.writer_username}</b><br></br>
                    <b>Comentariu: {review.comment}</b><br></br>
                    <StarRatings
                        rating={review.stars}
                        starRatedColor="orange"
                        numberOfStars={5}
                        name='rating'
                    />
                    <hr className="ui-widget-content" style={{ borderTop: 0 }} />
                </Panel>
            </div>
        );
    }

    getLinkFromNotification(notification) {
        let i = notification.message.indexOf("[");
        if (i === -1)
            return ""
        let j = notification.message.indexOf("]");
        if (j === -1)
            return ""

        return notification.message.slice(i + 1, j);
    }

    getMessageFromNotification(notification) {
        let j = notification.message.indexOf("]") + 1;
        if (j === -1)
            return ""
        let message = notification.message.slice(j, notification.message.size);
        return message;
    }

    renderNotification(notification) {
        return (
            <div style={{ padding: '.5em', cursor: "pointer" }} className="p-col-12 p-md-3" onClick={() => {
                this.props.history.push("/" + this.getLinkFromNotification(notification));
            }}>
                <Panel header={"Primit de la : " + notification.from_user} style={{ textAlign: 'center' }}>
                    {this.getMessageFromNotification(notification)}
                    <hr className="ui-widget-content" style={{ borderTop: 0 }} />
                </Panel>
            </div>
        )
    }

    getSelectedUsers() {
        let selectedUsers = [];
        this.state.users.forEach((user) => {
            if (this.state.selectedUser && user.includes(this.state.selectedUser)) {
                selectedUsers.push(user);
            }
        });
        return selectedUsers;
    }

    renderUser() {
        return (
            <div id="myprofiletab">
                <p id="usernameMyProfile">Nume utilizator: {this.state.user.username} </p>
                <p>Prenume: {this.state.user.first_name} </p>
                <p>Nume: {this.state.user.last_name}</p>
                <p>Telefon: {this.state.user.phone}</p>
                <p>Data nasterii: {new Date(this.state.user.date_of_birth).toLocaleDateString()}</p>
                <p>E-mail : {this.state.user.email}</p>
                <pre>Descriere: {this.state.user.bio}</pre>
                <StarRatings
                    rating={this.state.user.stars_average}
                    starRatedColor="orange"
                    numberOfStars={5}
                    name='rating'
                />
                <br></br>
                {this.state.user.username === LoginService.getId() ?
                    <Button id="b" label="Alege abilitati" onClick={() => {
                        this.props.history.push({
                            pathname: '/abilities',
                            user: this.state.user
                        });
                    }} /> : null}
                {this.state.user.username !== LoginService.getId() ?
                    <div className='p-search-users-container'>
                        <div className='p-autocomplete-search'>
                            <Autocomplete
                                getItemValue={(item) => item}
                                items={this.getSelectedUsers()}
                                renderItem={(item, isHighlighted) =>
                                    <div className='p-div-recomended' style={{ background: isHighlighted ? 'lightgray' : 'white' }}>
                                        {item}
                                    </div>}
                                onSelect={(e) => {
                                    this.setState({ selectedUser: e })
                                }}
                                onChange={(e) => {
                                    this.setState({ selectedUser: e.target.value })
                                }}
                                value={this.state.selectedUser}
                            />
                        </div>
                        <Button id="b" label="Recomanda acest user" onClick={() => {
                            let notification = this.state.notification;
                            notification.from_user = LoginService.getId();
                            notification.to_user = this.state.selectedUser;
                            notification.message = "[profile/" + this.state.user.username + "] RECOMANDARE : " + notification.from_user + " vi l-a recomandat pe " + this.state.user.username;
                            NotificationService.addNotification(notification).then((response) => {
                                NotificationManager.success('Recomandare user cu succes');
                            }, (error) => {
                                NotificationManager.error('Eroare' + error);
                            });
                            this.setState({ notification: notification, selectedUser: null });
                        }} />
                    </div> : null}
            </div>
        );
    }

    render() {
        return (<React.Fragment>
            {
                <div>
                    <div className="content-section introduction">

                    </div>
                    <div className="content-section implementation">
                        {this.state.user.username === LoginService.getId() ?
                            <TabView>
                                <TabPanel header="Profilul meu" leftIcon="pi pi-user">
                                    {this.renderUser()}
                                </TabPanel>
                                <TabPanel header="Joburi" leftIcon="fa fa-suitcase">
                                    {this.renderMyProfileJobs()}
                                </TabPanel>
                                <TabPanel header="Review-uri" leftIcon="fa fa-star-half-empty">
                                    <DataView value={this.state.reviews} itemTemplate={this.renderReview} />
                                </TabPanel>
                                <TabPanel header="Notifications" leftIcon="fa fa-bell">
                                    <DataView value={this.state.notifications} itemTemplate={this.renderNotification} />
                                </TabPanel>
                            </TabView> :
                            <TabView>
                                <TabPanel header={"Profilul lui: " + this.state.user.first_name + " " + this.state.user.last_name} leftIcon="pi pi-user">
                                    {this.renderUser()}
                                    {!this.state.selectedUser ?
                                        <div className='p-user-help-container'>
                                            <label> Roaga utilizatorul sa te ajute cu un job : &nbsp; </label>
                                            <Dropdown value={this.state.chosenPossibleJob} editable={true} autoWidth={true} options={this.state.possibleJobs} optionLabel={"name"} onChange={(event) => {
                                                this.setState({ chosenPossibleJob: event.target.value.name });
                                                let notification = this.state.notification;
                                                notification.message = "[job/" + event.target.value.id + "] Job sugerat: " + event.target.value.name;
                                                console.log(notification.message);
                                                notification.from_user = LoginService.getId();
                                                notification.to_user = this.state.user.username;
                                                this.setState({ notification: notification });
                                                this.setState({ posibelJobSelected: true });
                                            }} />
                                            {this.state.posibelJobSelected ?
                                                <Button label="Trimite propunerea" onClick={() => {
                                                    NotificationService.addNotification(this.state.notification);
                                                    this.setState({ posibelJobSelected: false });
                                                    NotificationManager.success('Felicitari ai adaugat propunerea.');
                                                }} /> : null
                                            }
                                        </div> : null}
                                </TabPanel>
                                <TabPanel header="Joburi" leftIcon="fa fa-suitcase">
                                    {this.renderMyProfileJobs()}
                                </TabPanel>
                                <TabPanel header="Review-uri" leftIcon="fa fa-star-half-empty">
                                    <DataView value={this.state.reviews} itemTemplate={this.renderReview} />
                                </TabPanel>
                            </TabView>
                        }


                    </div>
                </div>
            }
        </React.Fragment>);
    }

    renderMyProfileJobs() {
        return (
            <React.Fragment>
                {
                    <DataView value={this.state.jobs} layout="grid" itemTemplate={this.renderJob} />
                }
            </React.Fragment>
        )
    }
}