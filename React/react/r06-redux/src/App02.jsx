import { Provider } from 'react-redux';
import store from './store/store';
import AppChildComp from './components/AppChildComp';

function App() {
  //(4) Provider로 감싸기
  return (
    <>
      <h2>리덕스-부모컴포넌트(App): 분해</h2>
      <Provider store={store}>
        <AppChildComp />
      </Provider>
    </>
  );
}

export default App;
