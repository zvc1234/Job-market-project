import axios from 'axios';

export class JobService {
    static root = 'http://localhost:8080/';

    static addJob(jobModel) {
        return new Promise((resolve, reject) => {
            axios.post(this.root + "UserJob/addJob", jobModel).then((response) => {
                resolve(resolve.data);
            }, (error) => {
                reject(error);
            })
        })
    }

    static applyForJob(jobApplyModel) {
        return new Promise((resolve, reject) => {
            axios.post(this.root + "UserJob/registerForJob", jobApplyModel).then((response) => {
                resolve(resolve.data);
            }, (error) => {
                reject(error);
            })
        })
    }

    static getFilteredJobs(filterList) {
        let list = []
        filterList.forEach(category => {
            list.push({ name: category });
        })
        return new Promise((resolve, reject) => {
            axios.post(this.root + "UserCategory/getJobsFilteredByCategories", list).then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error);
            });
        });
    }

    static getShortJobs() {
        return new Promise((resolve, reject) => {
            axios.get(this.root + "UserCategory/getAllJobs").then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error);
            });
        });
    }

    static getAllAplicantsForAJob(idJob)
    {
        return new Promise((resolve, reject) =>{
            axios.get(this.root + "/User/getJobProviders?idJob=" + idJob).then((response) =>{
                resolve(response.data);
            }, (error) => {
                reject(error.response);
            })
        })
    }

    static getJobsForReceiver(receiver) {
        return new Promise((resolve, reject) => {
            axios.get(this.root + "UserCategory/getClientJobs?clientName="+receiver).then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error);
            });
        });
    }

    static deleteJob(jobId) {
        return new Promise((resolve, reject) => {
            axios.delete(this.root + "UserJob/deleteJob?idJob=" + jobId).then((response) => {
                resolve(response.data);
            }, (error) => {
                reject(error);
            })
        })
    }

    static getJob(jobId)
    {
        return new Promise((resolve, reject) => {
            axios.get(this.root + "UserCategory/getJob?idJob=" + jobId).then((response) =>{
                resolve(response.data);
            }, (error) => {
                reject(error);
            })
        })
    }


}