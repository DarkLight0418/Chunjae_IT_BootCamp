import { create } from 'zustand';

/*방법1
const useCountStore = create((set) => ({
  count: 0,
  increase: () => set(state => ({count: state.count+1})),
  decrease: () => set(state => ({count: state.count-1})), 
  reset: () => set({count: 0})
}))
export default useCountStore
*/

//방법2
const useCountStore = create((set, get) => ({
  count: 0,
  increase: () => {
    const current = get().count;
    if (current >= 5) {
      alert('최대값은 5입니다');
      return;
    }
    set({ count: current + 1 });
  },
  decrease: () => {
    const current = get().count;
    if (current <= -5) {
      alert('최소값은 -5입니다');
      return;
    }
    set({ count: current - 1 });
  },
  reset: () => set({ count: 0 }),
}));
export default useCountStore;
