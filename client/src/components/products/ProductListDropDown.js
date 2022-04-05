import React from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { productListDropDown } from '../../store/thunk/userThunkCreators';

export const ProductList = (products) => (
  <TextField

  id="productName"
  select
  label="Product Name"
  name="{products.itemName}"
  fullWidth={170}
  onChange={products.onChange}
  variant="outlined"
  SelectProps={{
    native: true,
  }}
  
>
 <option defaultValue>Product Name </option> 
     {products.options.map((item, index) => (
       <option key={index} value={item.id}>
        {item.itemName}
    </option>
  
  ))}
</TextField>
)
export default class ProductListDropDown extends React.Component {
  constructor(props) {
    super()
    this.state = {
      collection: [],
      value: '',
    }
  }
  componentDidMount() {
   
    productListDropDown().then((data) => {
      this.setState({ collection: data })
    })
    .catch(err => console.log("Axios err: ", err))
  }
  

  onChange = (event) => {
    this.setState({ value: event.target.value })
    this.props.onProductSelection(event.target.value); 
  }

 render() {
      return (
          <div> 
          <Box sx={{ m: 3}}></Box> 
          <ProductList
            name={this.state.username}
            options={this.state.collection}
            value={this.state.collection}
            onChange={this.onChange}
          />
        </div>
      )
    }
  }