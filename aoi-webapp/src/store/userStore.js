import { defineStore } from 'pinia';
import { getProfile } from '../api/userProfile.js';
import { onApiSuccess } from '../utils/assert.js';

export const useUserStore = defineStore('user', {
        state() {
            return {
                token: localStorage.getItem('token'),
                user: null
            };
        },
        getters: {
            signed() {
                return this.user !== null;
            },
            userProfile() {
                return this.user;
            }
        },
        actions: {
            userLogout() {
                localStorage.clear();
                window.location.href = '/';
            },
            userLogin(token) {
                this.token = token;
            },
            async getUserProfile() {
                const { code, data } = await getProfile();
                onApiSuccess(code, () => {
                    this.user = data;
                });
            }
        }
    })
;