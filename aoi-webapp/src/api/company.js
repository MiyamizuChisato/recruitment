import http from '../utils/http.js';

const BASE_PATH = '/company';
export const postCompany = (data) => {
    return http.post(`${BASE_PATH}/post/company`, data,
        { headers: { 'Content-Type': 'multipart/form-data' } });
};
export const getCompanies = () => {
    return http.get(`${BASE_PATH}/get/company`);
};
