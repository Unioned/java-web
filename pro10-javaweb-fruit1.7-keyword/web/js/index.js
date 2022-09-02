function delFruit(fid){
    if(confirm('是否确认删除?')){
        window.location.href = 'del.do?fid='+fid;
        /*给引用的servlet操作一个名为fid的变量*/
    }
}

function page(pageNo){
    window.location.href = 'index?pageNo='+pageNo;
    /*给引用的servlet操作一个名为pageNo的变量*/

}