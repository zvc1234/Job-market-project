import axios from 'axios';
export class UserService{
    static root = 'http://localhost:8080';

    static addUser(userModel){
        return new Promise((resolve, reject )=> {
            axios.post(this.root + "/User/createAccount", userModel).then((response) => {
                resolve(response);
            }, (error) => {
                reject(error.response.data);
            })
        })
    }

    static getAllUsernames() {
        return new Promise((resolve, reject) => {
            axios.get(this.root + "/User/getAllUsernames").then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error);
            });
        })
    }

    static getAllUsers(){
        return new Promise((resolve, reject) => {
            axios.get(this.root + "/User/getAllUsers").then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error);
            });
        })
    }

    static getUser(username){
        return new Promise((resolve, reject)=> {
            axios.get(this.root + "/User/getUser",{
                params:{
                    username : username
                }
            }).then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error.response.data);
            })
        })
    }

    static updateUser(userModel){
        return new Promise((resolve, reject)=> {
            axios.post(this.root + "/User/updateAccount", userModel).then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error.response.data);
            })
        })
    }


    static validateUser(userModel, errors)
    {
        let isValid =  true;
        let expr1 = /^[0-9a-zA-z_-]+$/

        if(userModel.username.match(expr1) === null)
        {
            errors.username = "Nume de utilizator invalid";
            isValid = false;
        }
        else
        {
            errors.username = "";
        }

        if(userModel.password === "")
        {
            errors.password = "Parola nu poate fi vida";
            isValid = false;
        }
        else
        {
            errors.password = "";
        }

        if(userModel.first_name === "")
        {
            errors.first_name = "Nume nu poate fi vid";
            isValid = false;
        }
        else
        {
            errors.first_name = "";
        }

        if(userModel.last_name === "")
        {
            errors.last_name = "Prenumele nu poate fi vid";
            isValid = false;
        }
        else
        {
            errors.last_name = "";
        }

        let diff =(new Date().getTime() - userModel.date_of_birth.getTime()) / 1000;
        diff /= (60 * 60 * 24);
        diff =  Math.abs(Math.round(diff/365.25));
        if(diff < 18)
        {
            errors.date_of_birth = "Trebuie sa aveti peste 18 ani";
            isValid = false;
        }
        else
        {
            errors.date_of_birth = "";
        }

        expr1 = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(userModel.email === "" || userModel.email.match(expr1) === null)
        {
            errors.email = "E-mail invalid";
            isValid = false;
        }
        else
        {
            errors.email = "";
        }

        expr1 = "[0-9]{10}";
        if(userModel.phone === "" || userModel.phone.match(expr1) === null)
        {
            errors.phone = "Telefon invalid";
            isValid = false;
        }
        else
        {
            errors.phone = "";
        }

        return isValid;
    }
}