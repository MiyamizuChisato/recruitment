import http from '../utils/http.js';

const BASE_PATH = '/user';

export const getProfile = () => {
    return http.get(`${BASE_PATH}/get/profile`);
};
export const putAvatar = (data) => {
    return http.put(`${BASE_PATH}/put/avatar`, data);
};
export const putResume = (data) => {
    return http.put(`${BASE_PATH}/put/resume`, data);
};
export const putProfile = (data) => {
    return http.put(`${BASE_PATH}/put/profile`, data);
};
export const getSimpleProfile = (id) => {
    return http.get(`${BASE_PATH}/get/simple/${id}`);
};