import { JobService } from '../Services/JobService';
import { CategoryService } from '../Services/CategoryService';
import React from 'react';
import {Card} from 'primereact/card';
import categorie_beauty from "../img/beauty.jpg"
import categorie_children from "../img/children.jpg"
import categorie_computer from "../img/computer.jpg"
import categorie_cooking from "../img/cooking.jpg"
import categorie_doctor from "../img/doctor.jpg"
import categorie_dog from "../img/dog.jpg"
import categorie_garden from "../img/garden.jpg"
import categorie_learning from "../img/learning.jpg"
import categorie_music from "../img/music.jpg"
import categorie_organization from "../img/organization.jpg"
import categorie_shopping from "../img/shopping.jpg"
import categorie_sport from "../img/sport.jpg"
import categorie_transport from "../img/transport.jpg"
import categorie_default from "../img/original.jpg"
import {Checkbox} from 'primereact/checkbox';
import "../css/Dashboard.css"

export class Dashboard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            jobs: [],
            categories: [],
            filter: new Set(),
        };
        this.onCategoryCheck = this.onCategoryCheck.bind(this);
    }

    loadJobs = () => {
        if (this.state.filter.size > 0) {
            JobService.getFilteredJobs(this.state.filter).then((response) => {
                this.setState({ jobs: response });
            }, (error) => {
                console.log(error.message);
            });
        } else {
            JobService.getShortJobs().then((response) => {
                this.setState({ jobs: response });
            }, (error) => {
                console.log(error.message);
            });
        }
    }

    getRandomColor()
    {
        return '#' + Math.random().toString(16).slice(2, 8).toUpperCase();
    }

    componentDidMount() {
        this.loadJobs();

        CategoryService.getCategories().then((response) => {
            let categories = response.map((category)=>category["name"]);
            this.setState({ categories: categories});
        }, (error) => {
            console.log(error.message);
        });

    }

    redirectToJob(job){
        this.props.history.push("/job/"+ job);
    }

    renderHeader(category){
        return(
            <img alt="Card" src={
                this.chooseCategory(category)
            } width="100px" height="150px"/>
        )
    }

    chooseCategory(category)
    {
        switch(category){
            case 'casa si gradina':
                return categorie_garden;
            case 'animale':
                return categorie_dog;
            case 'copii':
                return categorie_children;
            case 'IT':
                return categorie_computer;
            case 'cursuri si meditatie':
                return categorie_learning;
            case 'gatit':
                return categorie_cooking;
            case 'muzica':
                return categorie_music;
            case 'sport':
                return categorie_sport;
            case 'organizare evenimente':
                return categorie_organization;
            case 'transport':
                return categorie_transport;
            case 'cumparaturi si livrari':
                return categorie_shopping;
            case 'consultatii medicale':
                return categorie_doctor;
            case 'infrumusetare':
                return categorie_beauty;
            default:
                return categorie_default;
        }
    }

    onCategoryCheck(e)
    {
        let category = e.value;
        let filtered = this.state.filter;

        if(filtered.has(category)) 
            filtered.delete(category);
        else
            filtered.add(category);
        this.setState({filter : filtered});
        this.loadJobs();
    }

    render() {
        return (
            <React.Fragment>
                    <div>
                    <div style={{display : "flex"}}>
                    <div className="left-panel" style ={{height:"1000px"}}>
                        <h4>Categorie :</h4> 
                        {
                            this.state.categories.map((category)=>
                            (
                                <div>
                                <Checkbox inputId={category} 
                                    value={category} 
                                    onChange={this.onCategoryCheck} 
                                    checked={this.state.filter.has(category)}
                                    style={{margin: "5px"}}>
                                </Checkbox>
                                <label htmlFor={category} className="p-checkbox-label">{category}</label>
                                </div>
                            ))
                        }

                    </div>
                     <div style={{textAlign: "center", flex : 6}}>
                    {
                        this.state.jobs.map((job)=>
                        (
                            <div onClick={()=>{
                                this.props.history.push("/job/"+ job.id);
                            }}>
                            <Card title={job.title} 
                                subTitle={job.category}
                                className="card"
                                header={this.renderHeader(job.category)}
                                >
                            </Card>
                            
                            </div>
                        ))
                    }
                    </div> 
                    </div>
                    </div>                    
             </React.Fragment>);
    }
}

