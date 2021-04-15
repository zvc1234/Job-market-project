import axios from 'axios';

export class NotificationService {
    static root = 'http://localhost:8080/';

    static addNotification(notification) {
        return new Promise((resolve, reject) => {
            axios.post(this.root + "Notification/addNotification", notification).then((response) => {
                resolve(resolve.data);
            }, (error) => {
                reject(error);
            })
        })
    }

    static getNotificationsTo(to)
    {
        return new Promise((resolve, reject) =>{
            axios.get(this.root + "Notification/getAllNotificationsByTo?toNotification=" + to).then((response) =>{
                resolve(response.data);
            }, (error) => {
                reject(error.response);
            })
        })
    }

}