import React, { useEffect } from "react";
import MediaCard from "./MediaCard";
import brand from "../../assets/images/brand.png";
import feedback from "../../assets/images/feedback3.png";
import products from "../../assets/images/products.png";
import shop from "../../assets/images/shop.png";
import users from "../../assets/images/users.png";
import { useDispatch, useSelector } from "react-redux";
import { getStatistics } from "../../store/thunk/statisticsAdmin";

const AnalyticsAdmin = (props) => {
  const dispatch = useDispatch();
  const stats = useSelector((state) => state.statistics.stats);

  useEffect(() => {
    dispatch(getStatistics());
  });
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
      {<MediaCard description={stats.brand} title="Brands" image={brand} />}
      {<MediaCard description={stats.stores} title="Stores" image={shop} />}
      {
        <MediaCard
          description={stats.feedback}
          title="Feedbacks"
          image={feedback}
        />
      }
      {<MediaCard description={stats.users} title="Users" image={users} />}
      {
        <MediaCard
          description={stats.product}
          title="Products"
          image={products}
        />
      }
    </div>
  );
};

export default AnalyticsAdmin;
