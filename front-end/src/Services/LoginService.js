import axios from 'axios';
import Cookies from 'universal-cookie';
import { Base64 } from 'js-base64';

export class LoginService {
    static root = 'http://localhost:8080/';
    static cookies = new Cookies();

    static getId() {
        let data = this.cookies.get('SESSION_ID');

        if (data === undefined) {
            return "";
        }

        let session_id = Base64.decode(data).split(',');
        this.id = session_id[0];
        return this.id;
    }

    static setData(id) {
        this.cookies.set('SESSION_ID', Base64.encode(id));
    }

    static login(loginModel) {
        return new Promise((resolve, reject) => {
            axios.post(this.root + "User/login", loginModel).then((responese) => {
                resolve(responese);
            }, (error) => {
                reject(error);
            })
        });
    }

    static logout() {
        this.cookies.remove('SESSION_ID');
    }
}