import axios from 'axios';

export class ReviewService {
    static root = 'http://localhost:8080/';

    static addReview(reviewModel) {
        return new Promise((resolve, reject) => {
            axios.post(this.root + "Review/addReview", reviewModel).then((response) => {
                resolve(resolve.data);
            }, (error) => {
                reject(error);
            })
        })
    }

    static getReviewsByUsernameReceiver(username)
    {
        return new Promise((resolve, reject) => {
            axios.get(this.root + "Review/getReviewsByUsernameReceiver?usernameReceiver="+username).then((response)=>{
                resolve(response.data);
            }, (error)=>{
                reject(error);
            })
        })
    }
}