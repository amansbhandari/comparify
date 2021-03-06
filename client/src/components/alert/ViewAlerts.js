import React, { useEffect, useState } from "react";
import PropTypes from 'prop-types';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import IconButton from '@mui/material/IconButton';
import FirstPageIcon from '@mui/icons-material/FirstPage';
import DeleteIcon from '@mui/icons-material/Delete';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import LastPageIcon from '@mui/icons-material/LastPage';
import { TableHead } from '@mui/material';
import { useDispatch, useSelector } from "react-redux";

import { deleteAlert, fetchAlerts } from "../../store/thunk/alertThunkCreators";

function TablePaginationActions(props) {
    const theme = useTheme();
    const { count, page, rowsPerPage, onPageChange } = props;

    const handleFirstPageButtonClick = (event) => {
        onPageChange(event, 0);
    };

    const handleBackButtonClick = (event) => {
        onPageChange(event, page - 1);
    };

    const handleNextButtonClick = (event) => {
        onPageChange(event, page + 1);
    };

    const handleLastPageButtonClick = (event) => {
        onPageChange(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
    };

    return (
        <Box sx={{ flexShrink: 0, ml: 2.5 }}>
            <IconButton
                onClick={handleFirstPageButtonClick}
                disabled={page === 0}
                aria-label="first page"
            >
                {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
            </IconButton>
            <IconButton
                onClick={handleBackButtonClick}
                disabled={page === 0}
                aria-label="previous page"
            >
                {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
            </IconButton>
            <IconButton
                onClick={handleNextButtonClick}
                disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                aria-label="next page"
            >
                {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
            </IconButton>
            <IconButton
                onClick={handleLastPageButtonClick}
                disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                aria-label="last page"
            >
                {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
            </IconButton>
        </Box>
    );
}

TablePaginationActions.propTypes = {
    count: PropTypes.number.isRequired,
    onPageChange: PropTypes.func.isRequired,
    page: PropTypes.number.isRequired,
    rowsPerPage: PropTypes.number.isRequired,
};

function createData(id, alertIdentifier, item, brand, type) {
    return { id, alertIdentifier, item, brand, type };
}

const columns = [
    { id: 'alertIdentifier', label: 'Alert Identifier' },
    { id: 'item', label: 'Item' },
    { id: 'brand', label: 'Brand', },
    { id: 'type', label: 'Type' },
    { id: 'id', label: 'Actions', actions: true }
];

export default function ViewAlerts() {
    const theme = useTheme();
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const dispatch = useDispatch();
    const alerts = useSelector((state) => state.alert.alerts);

    const [rows, setRows] = useState([])

    useEffect(() => {
        dispatch(fetchAlerts())
    }, [dispatch])

    useEffect(() => {

        setRows(alerts.map(alert => createData(alert.id, alert.alert_identifier, alert.item.name, alert.brand.name, alert.type)));

    }, [alerts])

    const emptyRows =
        page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rows.currentlength) : 0;

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const handleDeleteAlert = (alertId, alertIdentifier) => {
        dispatch(deleteAlert(alertIdentifier, { id: alertId }))
    }

    return (
        <Paper sx={{ width: '100%', overflow: 'hidden' }}>
            <TableContainer sx={{ maxHeight: 500 }} >
                <Table stickyHeader sx={{ minWidth: 500 }} aria-label="custom pagination table">
                    <TableHead >
                        <TableRow sx={{
                            background: theme.palette.primary.main
                        }}>

                            {columns.map(column => {
                                return (<TableCell sx={{ color: "#fff", background: "#1976d2" }} key={column.id}>
                                    {column.label}
                                </TableCell>)
                            })}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {(rowsPerPage > 0
                            ? rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            : rows
                        ).map((row) => (
                            <TableRow hover key={row.id}
                                sx={{ cursor: "pointer" }}>
                                {columns.map(column => {
                                    if(column.actions){
                                        return (<TableCell key={column.id}>
                                            <DeleteIcon onClick={() => handleDeleteAlert(row.id, row.alertIdentifier)} />
                                        </TableCell>)    
                                    }

                                    return (<TableCell key={column.id}>
                                        {row[column.id]}
                                    </TableCell>)
                                })}
                            </TableRow>
                        ))}

                        {emptyRows > 0 && (
                            <TableRow style={{ height: 53 * emptyRows }}>
                                <TableCell colSpan={6} />
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>

            <TablePagination
                component={"div"}
                rowsPerPageOptions={[10, 20, 30, { label: 'All', value: -1 }]}
                colSpan={3}
                count={rows.length}
                rowsPerPage={rowsPerPage}
                page={page}
                SelectProps={{
                    inputProps: {
                        'aria-label': 'rows per page',
                    },
                    native: true,
                }}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
                ActionsComponent={TablePaginationActions}
            />
        </Paper>
    );
}
