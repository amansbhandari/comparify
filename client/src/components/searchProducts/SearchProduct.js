import React from "react";
import SearchBar from "./SearchBar";
import ProductList from "./ProductList";

class SearchProduct extends React.Component {
    
  render() {
    return (
      <div className="ui container" style={{ marginTOp: "10px" }}>
        <SearchBar/>
        <ProductList/>
      </div>
    );
  }
}

export default SearchProduct;
