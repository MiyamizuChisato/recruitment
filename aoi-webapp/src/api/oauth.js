import http from '../utils/http.js';

const BASE_PATH = '/oauth';

export const getPath = (type) => {
    return http.get(`${BASE_PATH}/get/path/${type}`);
};

export const postCode = (type, parma) => {
    return http.get(`${BASE_PATH}/post/${type}/login/${parma}`);
};