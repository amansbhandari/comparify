import React from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { brandListDropDown } from '../../store/thunk/userThunkCreators';
export const BrandList = (brands) => (
  <TextField

  id="brandName"
  select
  label="Brand Name"
  name="{brands.username}"
  fullWidth={170}
 onChange={brands.onChange}
  variant="outlined"
  SelectProps={{
    native: true,
  }}

>
 <option defaultValue>Brand Name {brands.name}</option> 
     {brands.options.map((item, index) => (
       <option key={index} value={item.id}>
        {item.name}
    </option>
  ))}
</TextField>
)
export default class BrandListDropDown extends React.Component {
  constructor(props) {
    super()
    this.state = {
      collection: [],
      value: '',
    }
  }
  componentDidMount() {
    
    brandListDropDown().then((data) => {
      this.setState({ collection: data })
    })
    .catch(err => console.log("Axios err: ", err))
  }
  

  onChange = (event) => {
    this.setState({ value: event.target.value })
    this.props.onBrandSelection(event.target.value); 
  }


 
    render() {
      return (
          <div> 
          <Box sx={{ m: 3}}></Box> 
          <BrandList
            name={this.state.username}
            options={this.state.collection}
            onChange={this.onChange}
          />
        </div>
      )
    }
  }