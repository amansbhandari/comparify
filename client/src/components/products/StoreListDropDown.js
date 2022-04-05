import React from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { storeListDropDown } from '../../store/thunk/userThunkCreators';
export const StoreList = (stores) => (
  <TextField

  id="store name"
  select
  label="Store Name"
  name="{stores.storeName}"
  fullWidth={170}
  onChange={stores.onChange}
  SelectProps={{
    native: true,
  }}
  variant="outlined"
>
 <option defaultValue>Store Name </option> 
     {stores.options.map((item, index) => (
       <option key={index} value={item.id}>
        {item.storeName}
    </option>
 ))}
 </TextField>
 )
export default class StoreListDropDown extends React.Component {
  constructor(props) {
    super()
    this.state = {
      temp: [],
      value: '',
    }
  }
  componentDidMount() {
    storeListDropDown().then((data) => {
      this.setState({ temp: data })
    })
    .catch(err => console.log("Axios err: ", err))
  }

  onChange = (event) => {
    this.setState({ value: event.target.value})
    this.props.onStoreSelection(event.target.value); 
  }

  render() {
    return (
        <div> 
        <Box sx={{ m: 3}}></Box>
        <StoreList
          name={this.state.storeName}
          options={this.state.temp}
          value={this.state.temp}
          onChange={this.onChange}
        />
      </div>
    )
  }
}