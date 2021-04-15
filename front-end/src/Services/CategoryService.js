import axios from 'axios';

export class CategoryService {
    static root = 'http://localhost:8080/';

    static getCategories() {
        return new Promise((resolve, reject) => {
            axios.get(this.root + "Category/getAllCategories").then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error);
            })
        })
    }
}