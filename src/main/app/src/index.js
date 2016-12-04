import * as d3 from 'd3'
import 'ace-css/css/ace.css'
import 'font-awesome/css/font-awesome.css'
import './main.css'

//add index.html to dist
import './index.html'

const margin = { top: 20, right: 10, bottom: 120, left: 60 }

const width = 640 - margin.left - margin.right
const height = 500 - margin.top - margin.bottom

const svg = d3.select("#chart").append("svg")
  .attr("width", width + margin.left + margin.right)
  .attr("height", height + margin.top + margin.bottom)
  .append("g")
  .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


d3.json(REST_URL + '/parks', data => {

  const parkData = data
    .map(({ park, ytd }) => ({ park, ytd: Number(ytd) }))
    .filter(({ park }) => park != "Totals:")
    .sort((a, b) => b.ytd - a.ytd)
    .filter((d, i) => i < 10)
    .sort((a, b) => {
      let nameA = a.park.toUpperCase()
      let nameB = b.park.toUpperCase();
      if (nameA < nameB) { return -1 }
      if (nameA > nameB) { return 1 }
      return 0
    })
    // console.log(parkData)

  const yScale = d3.scaleLinear()
    .domain([0, d3.max(parkData, d => d.ytd)])
    .range([height, 0])

  const xScale = d3.scaleBand()
    .padding(0.2)
    .domain(parkData.map(d => d.park))
    .range([0, width]);


  const xAxis = d3.axisBottom(xScale)
    .ticks(5)
    .tickSize(10)
    .tickPadding(5);


  const yAxis = d3.axisLeft(yScale);
  svg.call(yAxis);


  svg
    .append('g')
    .attr('transform', `translate(0, ${height})`)
    .call(xAxis)
    .selectAll('text')
    .style('text-anchor', 'end')
    .attr('transform', 'rotate(-45)');

  svg.selectAll('rect')
    .data(parkData)
    .enter()
    .append('rect')
    .attr('y', height)
    .attr('height', 0)
    .attr('x', d => xScale(d.park))
    .attr('width', _ => xScale.bandwidth())
    .attr('fill', 'steelblue')
    .transition(d3.transition().duration(1000))
    .ease(d3.easeBounceOut)
    .attr('y', d => yScale(d.ytd))
    .attr('height', d => height - yScale(d.ytd))

})
