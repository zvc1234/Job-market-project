import React from 'react'
import { PickList } from 'primereact/picklist';
import { Button } from 'primereact/components/button/Button';
import { UserService } from '../Services/UserService'
import { LoginService } from '../Services/LoginService';
import '../css/Abilities.css';

export class Abilities extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            total_abilities: ["Creativitate", "Imi plac copiii", "Gradinarit", "Gatesc bine", "Amuzant", "Imi plac animalele",
                "Am ureche muzicala", "Stiu canta la instrumente", "Stiu limbi straine", "Stiu matematica", "Negociator",
                "Imi place agricultura", "Bona/Menajera", "Cosmetician", "Frizer"],
            user_abilities: []
        }
        this.onChange = this.onChange.bind(this);
    }

    componentDidMount() {
        let userModel = this.props.location.user;
        let regex = /- .*/g;
        let abilities = userModel.bio.match(regex);
        if (abilities === null)
            return;
        abilities = abilities.map((ability) => ability.slice(2, ability.size));

        let total_abilities = this.state.total_abilities.filter(function (el) {
            return abilities.indexOf(el) < 0;
        });

        this.setState({ total_abilities: total_abilities });
        this.setState({ user_abilities: abilities });

        console.log(abilities)
    }

    onChange(event) {
        this.setState({
            total_abilities: event.source,
            user_abilities: event.target
        });
    }

    abilityTemplate(ability) {
        return (
            <div className="p-clearfix">
                <div style={{ fontSize: '14px', float: 'right', margin: '15px 5px 0 0' }}>
                    {ability}
                </div>
            </div>
        );
    }

    submitActivities() {
        let userModel = this.props.location.user;
        let bio = "";

        this.state.user_abilities.forEach((ability) => {
            bio += "- " + ability + "\n";
        })

        var i = userModel.bio.indexOf("\nAbilitati");
        if (i !== -1)
            userModel.bio = userModel.bio.slice(0, i);

        userModel["bio"] += "\nAbilitati : \n" + bio;
        UserService.updateUser(userModel).catch((error) => console.log(error));

        if (LoginService.getId() !== "")
            this.props.history.goBack();
        else
            this.props.history.push("/login");
    }

    render() {
        return (
            <div className='container-abilities'>
                <div style={{ marginLeft: '40px' }} className="content-section introduction">
                    <div className="feature-intro">
                        <h1>Abilitatile mele</h1>
                    </div>
                </div>

                <div className="content-section implementation">
                    <PickList source={this.state.total_abilities} target={this.state.user_abilities} itemTemplate={this.abilityTemplate}
                        sourceHeader="Abilitati curente" targetHeader="Abilitati selectate" responsive={true}
                        sourceStyle={{ height: '300px' }} targetStyle={{ height: '300px' }}
                        onChange={this.onChange}
                        showSourceControls={false}
                        showTargetControls={false}
                        style={{ width: '600px', marginLeft: '40px' }}
                    >
                    </PickList>
                </div>

                <Button id="b" label="Salveaza" onClick={(v) => {
                    this.submitActivities();
                }} />
            </div>
        );
    }

}