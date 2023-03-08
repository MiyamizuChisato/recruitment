import http from '../utils/http.js';

const BASE_PATH = '/auth';
export const register = (user) => {
    return http.post(`${BASE_PATH}/register`, user);
};
export const login = (user) => {
    return http.post(`${BASE_PATH}/login`, user);
};