import http from '../utils/http.js';

const BASE_PATH = '/relation';
export const getReceivers = (current) => {
    return http.get(`${BASE_PATH}/get/receivers/page/${current}`);
};
export const validateRelation = (sender, receiver) => {
    return http.post(`${BASE_PATH}/validate/sender/${sender}/receiver/${receiver}`);
};