import { configureStore } from '@reduxjs/toolkit';
import reducer from './countSlice';

//(2) store 생성
const store = configureStore({
  reducer: {
    count: reducer,
  },
});

export default store;
