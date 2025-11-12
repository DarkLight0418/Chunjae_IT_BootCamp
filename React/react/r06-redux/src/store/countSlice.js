import { createSlice } from '@reduxjs/toolkit';

//(1) slice 정의
const countSlice = createSlice({
  name: 'count',
  initialState: { value: 0 },
  reducers: {
    increase: (state) => {
      state.value += 1;
    },
    decrease: (state) => {
      state.value -= 1;
    },
    reset: (state) => {
      state.value = 0;
    },
  },
});
export const { increase, decrease, reset } = countSlice.actions;
export default countSlice.reducer;
