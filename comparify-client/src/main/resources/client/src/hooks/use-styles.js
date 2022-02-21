import { makeStyles } from "@material-ui/core/styles";

const useStyles = (style) => {
  return makeStyles(() => style)();
};

export default useStyles;
