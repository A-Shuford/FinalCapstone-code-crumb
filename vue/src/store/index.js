import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      message: {},
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      
      SET_SUCCESS(state, messageText) {
        this.commit('SET_MESSAGE', {
          level: "S",
          text: messageText,
        })
      },
      SET_ERROR(state, messageText) {
        this.commit('SET_MESSAGE', {
          level: "E",
          text: messageText,
        })
      },
      SET_MESSAGE(state, message) {
        // Set the message
        state.message = message;
      },
      CLEAR_MESSAGE(state) {
        state.message = {};
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },
  });
  return store;
}
