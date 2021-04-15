import * as React from "react";
import { InputText } from 'primereact/components/inputtext/InputText';
import { Button } from 'primereact/components/button/Button';
import { NotificationManager } from 'react-notifications';
import { InputTextarea } from 'primereact/inputtextarea';
import { Dropdown } from 'primereact/dropdown';
import { JobService } from "../Services/JobService";
import { LoginService } from "../Services/LoginService";
import { CategoryService } from "../Services/CategoryService"
import '../css/JobForm.css'

export class JobForm extends React.Component {

    constructor(usersProps) {
        super(usersProps);

        this.state = {
            jobModel: {
                description: "",
                title: "",
                categoryName: "",
                username: LoginService.getId(),
            },
            categories: []
        };
    }

    submit() {
        JobService.addJob(this.state.jobModel).then(() => {
            //NotificationManager.success("Job adaugat cu succes !", "Felicitari !");
            this.props.history.push('/dashboard');
        }, (error) => {
            NotificationManager.error(error.message);
        });
    }

    componentDidMount() {
        CategoryService.getCategories().then((response) => {
            this.setState({ categories: response });
        }, (error) => {
            NotificationManager.error(error.message);
        })
    }

    render() {
        return (
            <div className="container">
                <div className="p-grid p-fluid">
                    <div className="p-col-12 p-md-4" >
                        <label> Titlu: </label>
                        <InputText id="name" type="text" size={70} onChange={(event) => {
                            let jobModel = this.state.jobModel;
                            jobModel.title = event.target.value;
                            this.setState({ jobModel: jobModel });
                        }} />
                    </div>
                    <br /> <br />
                    <div className="p-col-12 p-md-4">
                        <label> Categorie: </label>
                        <Dropdown value={this.state.jobModel.categoryName} editable={true} autoWidth={true}  options={this.state.categories} optionLabel={"name"} onChange={(event) => {
                            let jobModel = this.state.jobModel;
                            jobModel.categoryName = event.target.value.name;
                            this.setState({ jobModel: jobModel });
                        }} />
                    </div>
                    <br /> <br />
                    <div className="p-col-12 p-md-4">
                        <label > Descriere: </label>
                        <InputTextarea rows={5} cols={60} autoResize={true} onChange={(event) => {
                            let jobModel = this.state.jobModel;
                            jobModel.description = event.target.value;
                            this.setState({ jobModel: jobModel });
                        }} />
                    </div>
                    <br /> <br />
                    <Button id="b" label="Adauga job" onClick={() => this.submit()} />
                </div>
            </div>
        );
    }
}