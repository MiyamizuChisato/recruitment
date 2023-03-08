import http from '../utils/http.js';

const BASE_PATH = '/mail';
export const sendVerifyMail = (email) => {
    return http.get(`${BASE_PATH}/verify/${email}`);
};
