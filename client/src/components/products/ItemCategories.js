import React from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { itemCategories } from '../../store/thunk/userThunkCreators';


export const ItemList = (categories) => (
  <TextField
  id="categoryName"
  select
  label="Category Name"
  name="{categories.categoryName}"
  fullWidth={170}
  onChange={categories.onChange}
  variant="outlined"
  SelectProps={{
    native: true,
  }}
 
>
 <option defaultValue>Category Name </option> 
     {categories.options.map((item, index) => (
       <option key={index} value={item.id}>
        {item.categoryName}
    </option>
  ))}
</TextField>
)
export default class ItemCategories extends React.Component {
  constructor(props) {
    super()
    this.state = {
      collection: [],
      value: '',
    }
  }

  
componentDidMount() {
   
itemCategories().then((data) => {
     this.setState({ collection: data })
   })
   .catch(err => console.log("Axios err: ", err))
}
  onChange = (event) => {
    this.setState({ value: event.target.value })
    this.props.onCategorySelection(event.target.value); 
  }

render() {
      return (
          <div> 
          <Box sx={{ m: 3}}></Box> 
          <ItemList
            name={this.state.categoryName}
            options={this.state.collection}
            value={this.state.collection}
            onChange={this.onChange}
          />
        </div>
      )
    }
  }