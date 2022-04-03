import React from "react";
import MediaCard from "./MediaCard";
import brand from "../../assets/images/brand.png";
import feedback from "../../assets/images/feedback3.png";
import products from "../../assets/images/products.png";
import shop from "../../assets/images/shop.png";
import users from "../../assets/images/users.png";

const AnalyticsAdmin = (props) => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        gap: "200px",
        marginTop: "10%",
        flexWrap: "wrap",
        flexBasis: "33%",
      }}
    >
      {<MediaCard description="100" title="Brands" image={brand} />}
      {<MediaCard description="90" title="Stores" image={shop} />}
      {<MediaCard description="80" title="Feedbacks" image={feedback} />}
      {<MediaCard description="70" title="Users" image={users} />}
      {<MediaCard description="50" title="Products" image={products} />}
    </div>
  );
};

export default AnalyticsAdmin;
